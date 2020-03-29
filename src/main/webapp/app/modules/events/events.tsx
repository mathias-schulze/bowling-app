import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

import { IRootState } from 'app/shared/reducers';
import { getEvents } from './events.reducer';

export interface IEventsProp extends StateProps, DispatchProps {}

export const Events = (props: IEventsProp) => {
  
  useEffect(() => {
    props.getEvents();
  }, []);
  
  const eventsArray = [];
  Object.keys(props.events).forEach(function(key) {
      eventsArray.push(props.events[key]);
    });
  const eventsList = eventsArray
    .sort((a, b) => (a.datum < b.datum ? 1 : -1));
  
  return (
    <Row>
      <Col md="9">
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Datum</TableCell>
                <TableCell>Beschreibung</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {eventsList.map((event) => (
                <TableRow key={event.key}>
                  <TableCell component="th" scope="row">
                    {event.datum}
                  </TableCell>
                  <TableCell component="th" scope="row">
                    {event.beschreibung}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ events }: IRootState) => ({
  events: events.events,
  isFetching: events.loading
});

const mapDispatchToProps = { getEvents };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Events);
